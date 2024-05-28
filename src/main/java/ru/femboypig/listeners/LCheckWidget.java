package ru.femboypig.listeners;

import net.minecraft.client.gui.widget.CheckboxWidget;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class LCheckWidget extends CheckboxWidget {
    public final Consumer<LCheckWidget> listener;
    public LCheckWidget(int x, int y, int width, int height, Text message, boolean checked, Consumer<LCheckWidget> listener) {
        super(x, y, width, height, message, checked);
        this.listener = listener;
    }

    public LCheckWidget(int x, int y, int width, int height, Text message, boolean checked, boolean showMessage, Consumer<LCheckWidget> listener) {
        super(x, y, width, height, message, checked, showMessage);
        this.listener = listener;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        listener.accept(this);
    }
}