package io.github.simplycmd.terracraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.simplycmd.terracraft.items.util.Value;
import io.github.simplycmd.terracraft.registry.ItemRegistry;
import io.github.simplycmd.terracraft.util.Offer;
import io.github.simplycmd.terracraft.util.OfferList;
import io.github.simplycmd.terracraft.util.PlayerEntityExtension;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

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
        this.addDrawableChild(new WidgetSellButton(i + 204, j + 57, 0, (button -> {
            this.handler.switchToSellScreen();
        })));
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
        drawTexture(matrices, i+107, j+17, this.getZOffset(), 276, 38, 18, 48, 512, 256);
        if(displayItemCompact())
            drawTexture(matrices, i+251, j+7, this.getZOffset(), 340, 0, 18, 66, 512, 256);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        //this.playerInventoryTitleX = 107+20;
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        int k = j + 16 + 1;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, TEXTURE);
        //drawTexture(matrices, i+215, j+8, this.getZOffset(), 276, 38, 48, 18, 512, 256);
        // 218, 8
        if (showItemDisplays()) {
            var d = this.getOffers().size() <= this.selectedIndex ? new Value(0).getMoney() : this.getOffers().get(this.selectedIndex).getValue().getMoney();
            this.itemRenderer.zOffset = 100.0F;
            this.itemRenderer.renderInGui(new ItemStack(ItemRegistry.platinum_coin.getItem()), i+252, j+8);
            this.itemRenderer.renderInGui(new ItemStack(ItemRegistry.gold_coin.getItem()), i+252, j+8+16);
            this.itemRenderer.renderInGui(new ItemStack(ItemRegistry.silver_coin.getItem()), i+252, j+8+16+16);
            this.itemRenderer.renderInGui(new ItemStack(ItemRegistry.copper_coin.getItem()), i+252, j+8+16+16+16);
            if (displayItemCompact()) {
                this.itemRenderer.renderGuiItemOverlay(this.textRenderer, new ItemStack(ItemRegistry.platinum_coin.getItem()), i+252, j+8, ""+d.getPlatinum());
                this.itemRenderer.renderGuiItemOverlay(this.textRenderer, new ItemStack(ItemRegistry.gold_coin.getItem()), i+252, j+8+16, ""+d.getGold());
                this.itemRenderer.renderGuiItemOverlay(this.textRenderer, new ItemStack(ItemRegistry.silver_coin.getItem()), i+252, j+8+16+16, ""+d.getSilver());
                this.itemRenderer.renderGuiItemOverlay(this.textRenderer, new ItemStack(ItemRegistry.copper_coin.getItem()), i+252,j+8+16+16+16, ""+d.getCopper());
            } else {
                this.textRenderer.draw(matrices, "x" + d.getPlatinum(), 136 + 50 + 16, 45 + (float) this.textRenderer.fontHeight / 2, 4210752);
                this.textRenderer.draw(matrices, "x" + d.getGold(), 136 + 50 + 16, 55 + 6 + (float) this.textRenderer.fontHeight / 2, 4210752);
                this.textRenderer.draw(matrices, "x" + d.getSilver(), 136 + 50 + 16, 65 + 12 + (float) this.textRenderer.fontHeight / 2, 4210752);
                this.textRenderer.draw(matrices, "x" + d.getCopper(), 136 + 50 + 16, 75 + 18 + (float) this.textRenderer.fontHeight / 2, 4210752);
            }
            this.itemRenderer.zOffset = 0.0F;
        }
        var offers = getOffers();
        renderScrollbar(matrices, i, j, offers);
        int m = 0;
        for (var offer : offers) {
            if (this.canScroll(offers.size()) && (m < this.indexStartOffset || m >= 7 + this.indexStartOffset)) {
                ++m;
            } else {
                this.itemRenderer.zOffset = 100.0F;
                int n = k + 2;
                this.itemRenderer.renderInGui(offer.getItem(), i + 5 + 68, n);
                this.itemRenderer.renderGuiItemOverlay(this.textRenderer, offer.getItem(), i + 5 + 68, n);
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

    class WidgetSellButton extends ButtonWidget {

        public WidgetSellButton(int x, int y, int index, PressAction onPress) {
            super(x, y, 45, 20, new LiteralText("Sell"), onPress);
        }
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
                    itemStack = BuyScreen.this.getOffers().get(this.index + BuyScreen.this.indexStartOffset).getItem();
                    BuyScreen.this.renderTooltip(matrices, itemStack, mouseX, mouseY);
                }
            }

        }

        @Override
        public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
            super.render(matrices, mouseX, mouseY, delta);
            if (BuyScreen.this.getOffers().size() <= this.index) return;
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

    private boolean showItemDisplays() {
        return true;
    }

    private boolean displayItemCompact() {
        return true;
    }

    @Override
    protected void drawSlot(MatrixStack matrices, Slot slot) {
        super.drawSlot(matrices, slot);
        int i = slot.x;
        int j = slot.y;
        var d = this.handler.slots.indexOf(slot);
        //this.textRenderer.draw(matrices, "" + d, i, j, 0x222222);
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        //super.drawForeground(matrices, mouseX, mouseY);
        this.textRenderer.draw(matrices, "TM: " + ((PlayerEntityExtension)MinecraftClient.getInstance().player).getTemporaryMoney(), i, j-this.textRenderer.fontHeight, 0xffffff);
        this.textRenderer.draw(matrices, this.playerInventoryTitle, (float)this.playerInventoryTitleX, (float)this.playerInventoryTitleY, 4210752);

        int l = this.textRenderer.getWidth("Offers");
        this.textRenderer.draw(matrices, "Offers", (float)(5 - l / 2 + 48), 6.0F, 4210752);
    }
}
