package ru.femboypig.modmenu;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import ru.femboypig.listeners.LCheckWidget;

import java.text.DecimalFormat;

public class SAS extends Screen{
    public static final DecimalFormat FORMAT = new DecimalFormat("#.##");
    public static final DecimalFormat FORMATE = new DecimalFormat("#");

    public final Screen prevision;

    public CheckboxWidget enabledAnim;
    public CheckboxWidget enabledMainHand;
    public CheckboxWidget enabledOffHand;
    public CheckboxWidget slowAnim;
    public CheckboxWidget affectoffhand;


    public SliderWidget slowAnimValue;
    public SliderWidget positionX;
    public SliderWidget positionY;
    public SliderWidget positionZ;
    public SliderWidget scaleoff;
    public SliderWidget scale;

    public SAS(Screen prevision) {
        super(Text.translatable("anim.title"));
        this.prevision = prevision;
    }

    @Override
    public void init() {
        if (enabledAnim != null) removed();

        enabledAnim = addDrawableChild(new LCheckWidget(width / 2 - textRenderer.getWidth(Text.translatable("anim.enabledAnim")) / 2 - 1,
                30, 24 + textRenderer.getWidth(Text.translatable("anim.enabledAnim")), 20,
                Text.translatable("anim.enabledAnim"), SAC.enabledAnim, cb -> SAC.enabledAnim = cb.isChecked()));

        enabledMainHand = addDrawableChild(new LCheckWidget(width / 2 - textRenderer.getWidth(Text.translatable("anim.enabledMainHand")) / 2 - 220,
                30, 24 + textRenderer.getWidth(Text.translatable("anim.enabledMainHand")), 20,
                Text.translatable("anim.enabledMainHand"), SAC.enabledMainHand, cb -> SAC.enabledMainHand = cb.isChecked()));

        enabledOffHand = addDrawableChild(new LCheckWidget(width / 2 - textRenderer.getWidth(Text.translatable("anim.enabledOffHand")) / 2 - 222,
                60, 24 + textRenderer.getWidth(Text.translatable("anim.enabledOffHand")), 20,
                Text.translatable("anim.enabledOffHand"), SAC.enabledOffHand, cb -> SAC.enabledOffHand = cb.isChecked()));

        slowAnim = addDrawableChild(new LCheckWidget(width / 2 - textRenderer.getWidth(Text.translatable("anim.slowAnim")) / 2 + 210,
                30, 24 + textRenderer.getWidth(Text.translatable("anim.slowAnim")), 20,
                Text.translatable("anim.slowAnim"), SAC.slowAnim, cb -> SAC.slowAnim = cb.isChecked()));

        slowAnimValue = addDrawableChild(new SliderWidget(width / 2 + 153, 60, 150, 20,
                ScreenTexts.composeGenericOptionText(Text.translatable("anim.slowAnimValue"),
                        Text.translatable(FORMATE.format(SAC.slowAnimSpeed))), SAC.slowAnimValue) {

            public void updateMessage() {
                setMessage(ScreenTexts.composeGenericOptionText(Text.translatable("anim.slowAnimValue"),
                        Text.literal(FORMATE.format(1 + value * (50 - 1)))));
            }

            @Override
            public void applyValue() {
                SAC.slowAnimValue = (float) value;
                SAC.calculateSpeedAnim();
            }
        });
        affectoffhand = addDrawableChild(new LCheckWidget(width / 2 - textRenderer.getWidth(Text.translatable("anim.affectoffhand")) / 2 - 10,
                60, 24 + textRenderer.getWidth(Text.translatable("anim.affectoffhand")), 20,
                Text.translatable("anim.affectoffhand"), SAC.affectoffhand, cb -> SAC.affectoffhand = cb.isChecked()));

        positionX = addDrawableChild(new SliderWidget(width / 2 - 50, 90, 150, 20,
                ScreenTexts.composeGenericOptionText(Text.translatable("anim.positionX"),
                        Text.translatable(FORMAT.format(SAC.getPositionX))), SAC.positionX) {

            public void updateMessage() {
                setMessage(ScreenTexts.composeGenericOptionText(Text.translatable("anim.positionX"),
                        Text.literal(FORMAT.format(-180 + value * (180 - (-180))))));
            }

            @Override
            public void applyValue() {
                SAC.positionX = (float) value;
                SAC.calculatePosX();
            }
        });
        positionY = addDrawableChild(new SliderWidget(width / 2 - 50, 120, 150, 20,
                ScreenTexts.composeGenericOptionText(Text.translatable("anim.positionY"),
                        Text.translatable(FORMAT.format(SAC.getPositionY))), SAC.positionY) {

            public void updateMessage() {
                setMessage(ScreenTexts.composeGenericOptionText(Text.translatable("anim.positionY"),
                        Text.literal(FORMAT.format(-180 + value * (180 - (-180))))));
            }

            @Override
            public void applyValue() {
                SAC.positionY = (float) value;
                SAC.calculatePosY();
            }
        });
        positionZ = addDrawableChild(new SliderWidget(width / 2 - 50, 150, 150, 20,
                ScreenTexts.composeGenericOptionText(Text.translatable("anim.positionZ"),
                        Text.translatable(FORMAT.format(SAC.getPositionZ))), SAC.positionZ) {

            public void updateMessage() {
                setMessage(ScreenTexts.composeGenericOptionText(Text.translatable("anim.positionZ"),
                        Text.literal(FORMAT.format(-180 + value * (180 - (-180))))));
            }

            @Override
            public void applyValue() {
                SAC.positionZ = (float) value;
                SAC.calculatePosZ();
            }
        });
        scaleoff = addDrawableChild(new SliderWidget(width / 2 - 50, 180, 150, 20,
                ScreenTexts.composeGenericOptionText(Text.translatable("anim.scaleoff"),
                        Text.translatable(FORMAT.format(SAC.getScaleoff))), SAC.scaleoff) {

            public void updateMessage() {
                setMessage(ScreenTexts.composeGenericOptionText(Text.translatable("anim.scaleoff"),
                        Text.literal(FORMAT.format(0.1f + value * (3 - 0.1f)))));
            }

            @Override
            public void applyValue() {
                SAC.scaleoff = (float) value;
                SAC.caclculateScaleoff();
            }
        });
        scale = addDrawableChild(new SliderWidget(width / 2 - 50, 210, 150, 20,
                ScreenTexts.composeGenericOptionText(Text.translatable("anim.scale"),
                        Text.translatable(FORMAT.format(SAC.getScale))), SAC.scale) {

            public void updateMessage() {
                setMessage(ScreenTexts.composeGenericOptionText(Text.translatable("anim.scale"),
                        Text.literal(FORMAT.format(0.1f + value * (1 - 0.1f)))));
            }

            @Override
            public void applyValue() {
                SAC.scale = (float) value;
                SAC.caclculateScale();
            }
        });
    }

    // Save config
    @Override
    public void removed() {
        SAC.save();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        context.drawCenteredTextWithShadow(textRenderer, Text.translatable("anim.title"), width / 2, 8, 0xffffff);
        super.render(context, mouseX, mouseY, delta);
    }
}