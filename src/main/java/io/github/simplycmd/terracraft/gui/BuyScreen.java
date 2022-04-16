package io.github.simplycmd.terracraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.util.Offer;
import io.github.simplycmd.terracraft.util.OfferList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.ingame.Generic3x3ContainerScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.SelectMerchantTradeC2SPacket;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;

import java.util.Iterator;
import java.util.Random;

import static io.github.simplycmd.terracraft.packets.PacketHandler.CHANGE_OFFER;

public class BuyScreen extends HandledScreen<BuyScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("terracraft:textures/gui/container/coin_interface.png");
    private int indexStartOffset;
    private boolean scrolling = false;
    private final WidgetButtonPage[] offers = new WidgetButtonPage[7];
    private int selectedIndex;
    public BuyScreen(BuyScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        playerInventoryTitleX = 108;
        //this.titleX = 29;
        this.backgroundWidth = 276;
        playerInventoryTitleX = 107;
    }

    @Override
    protected void init() {
        super.init();
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        int k = j + 16 + 2;
        for(int l = 0; l < 7; ++l) {
            this.offers[l] = this.addDrawableChild(new WidgetButtonPage(i + 5, k, l, (button) -> {
                if (button instanceof WidgetButtonPage widgetButtonPage) {
                    this.selectedIndex = widgetButtonPage.index + this.indexStartOffset;
                    this.syncRecipeIndex();
                }

            }));
            k += 20;
        }
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        //super.drawBackground();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        drawTexture(matrices, i, j, this.getZOffset(), 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 512, 256);

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        int k = j + 16 + 1;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        var offers = getOffers();
        renderScrollbar(matrices, i, j, offers);
        int m = 0;
        for (var offer : offers) {
            if (this.canScroll(offers.size()) && (m < this.indexStartOffset || m >= 7 + this.indexStartOffset)) {
                ++m;
            } else {
                this.itemRenderer.zOffset = 100.0F;
                int n = k + 2;
                this.itemRenderer.renderInGui(offer.getItem().getDefaultStack(), i + 5 + 68, n);
                this.itemRenderer.renderGuiItemOverlay(this.textRenderer, offer.getItem().getDefaultStack(), i + 5 + 68, n);
                ++m;
                k += 20;
                this.itemRenderer.zOffset = 0.0F;
                //this.textRenderer.drawWithShadow(matrices, Integer.toString(offer.getValue().getValue()), i, n, 0xFFFFFF);
                this.renderArrow(matrices, offer, i, n);
            }
        }


        for (WidgetButtonPage widgetButtonPage : this.offers) {
            if (widgetButtonPage.isHovered()) {
                widgetButtonPage.renderTooltip(matrices, mouseX, mouseY);
            }

            widgetButtonPage.visible = widgetButtonPage.index < this.getOffers().size();
        }

        RenderSystem.enableDepthTest();
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    private void renderScrollbar(MatrixStack matrices, int x, int y, OfferList offers) {
        int i = offers.size() + 1 - 7;
        if (i > 1) {
            int j = 139 - (27 + (i - 1) * 139 / i);
            int k = 1 + j / i + 139 / i;
            int m = Math.min(113, this.indexStartOffset * k);
            if (this.indexStartOffset == i - 1) {
                m = 113;
            }

            drawTexture(matrices, x + 94, y + 18 + m, this.getZOffset(), 0.0F, 199.0F, 6, 27, 512, 256);
        } else {
            drawTexture(matrices, x + 94, y + 18, this.getZOffset(), 6.0F, 199.0F, 6, 27, 512, 256);
        }

    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
        int i = this.getOffers().size();
        if (this.canScroll(i)) {
            int j = i - 7;
            this.indexStartOffset = MathHelper.clamp((int)((double)this.indexStartOffset - amount), 0, j);
        }

        return true;
    }
    private boolean canScroll(int amount) {
        return amount > 7;
    }

    private OfferList getOffers() {
        return this.handler.getRecipes();
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        int i = this.getOffers().size();
        if (this.scrolling) {
            int j = this.y + 18;
            int k = j + 139;
            int l = i - 7;
            float f = ((float)mouseY - (float)j - 13.5F) / ((float)(k - j) - 27.0F);
            f = f * (float)l + 0.5F;
            this.indexStartOffset = MathHelper.clamp((int)f, 0, l);
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.scrolling = false;
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        if (this.canScroll(this.getOffers().size()) && mouseX > (double)(i + 94) && mouseX < (double)(i + 94 + 6) && mouseY > (double)(j + 18) && mouseY <= (double)(j + 18 + 139 + 1)) {
            this.scrolling = true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Environment(EnvType.CLIENT)
    class WidgetButtonPage extends ButtonWidget {
        final int index;

        public WidgetButtonPage(int x, int y, int index, PressAction onPress) {
            super(x, y, 89, 20, LiteralText.EMPTY, onPress);
            this.index = index;
            this.visible = false;
        }

        public int getIndex() {
            return this.index;
        }

        public void renderTooltip(MatrixStack matrices, int mouseX, int mouseY) {
            if (this.hovered && BuyScreen.this.getOffers().size() > this.index +  BuyScreen.this.indexStartOffset) {
                ItemStack itemStack;
                if (mouseX > this.x + 65) {
                    itemStack = BuyScreen.this.getOffers().get(this.index + BuyScreen.this.indexStartOffset).getItem().getDefaultStack();
                    BuyScreen.this.renderTooltip(matrices, itemStack, mouseX, mouseY);
                }
            }

        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            super.render(matrices, mouseX, mouseY, delta);
            var offer = BuyScreen.this.getOffers().get(this.index + BuyScreen.this.indexStartOffset);
            BuyScreen.this.textRenderer.drawWithShadow(matrices, "$" + offer.getValue().getValue(), calcX() - BuyScreen.this.textRenderer.getWidth("$" + offer.getValue().getValue()), this.y + ((float)this.height/2)-(float)BuyScreen.this.textRenderer.fontHeight/2, 0xFFFFFF);
        }

        private float calcX() {
            int i = (BuyScreen.this.width - BuyScreen.this.backgroundWidth) / 2;
            int q = i + 5 + 35 + 20;
            return q-3;
        }
    }

    private void syncRecipeIndex() {
        this.handler.setRecipeIndex(this.selectedIndex);
        this.handler.switchTo(this.selectedIndex);
        var l = PacketByteBufs.create();
        l.writeInt(this.selectedIndex);
        ClientPlayNetworking.send(CHANGE_OFFER, l);
        //this.client.getNetworkHandler().sendPacket(new SelectMerchantTradeC2SPacket(this.selectedIndex));
    }

    private void renderArrow(MatrixStack matrices, Offer tradeOffer, int x, int y) {
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        if (tradeOffer.isDisabled()) {
            drawTexture(matrices, x + 5 + 35 + 20, y + 3, this.getZOffset(), 25.0F, 171.0F, 10, 9, 512, 256);
        } else {
            drawTexture(matrices, x + 5 + 35 + 20, y + 3, this.getZOffset(), 15.0F, 171.0F, 10, 9, 512, 256);
        }

    }
}
