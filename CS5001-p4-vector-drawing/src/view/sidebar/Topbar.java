package view.sidebar;

import shapes.Shape;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Created by un4 on 10/11/16.
 */
public class Topbar extends JPanel {

    private JSlider slider;
    private int sliderChange;
    private ITopBar iTopBar;

    /**
     * The topbar constructor.
     */
    public Topbar() {
        this.slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        this.slider.addChangeListener(e -> {
            iTopBar.changeStrokeWidth(((JSlider) e.getSource()).getValue());
        });
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setValue(Shape.DEFAULT_STROKE);                                                                     
        this.add(slider);
    }

    /**
     * Sets the topbar delegate.
     *
     * @param iTopBar the delegate.
     */
    public void setiTopBar(ITopBar iTopBar) {
        this.iTopBar = iTopBar;
    }
}
