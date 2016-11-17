package view.sidebar;

import shapes.Shape;
import sun.security.provider.SHA;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Color;

/**
 * Created by un4 on 10/11/16.
 */
public class Topbar extends JPanel implements IColorChange {
    private JSlider slider;
    private ITopBar iTopBar;
    private JLabel sliderValue;
    private JLabel sliderLabel;
    private JButton fillColor;
    private JButton strokeColor;

    /**
     * The topbar constructor.
     */
    public Topbar() {
        this.slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
        this.sliderValue = new JLabel();
        this.fillColor = createButton("Fill");
        this.strokeColor = createButton("Stroke");
        this.fillColor.setBackground(Shape.DEFAULT_COLOR);
        this.strokeColor.setBackground(Shape.DEFAULT_STROKE_COLOR);

        this.sliderLabel = new JLabel("Change Stroke Width");
        this.slider.addChangeListener(e -> {
            if (iTopBar != null) {
                int value = ((JSlider) e.getSource()).getValue();
                sliderValue.setText(String.valueOf(value));
                iTopBar.changeStrokeWidth(value);
            }
        });
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(25);
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setValue(Shape.DEFAULT_STROKE);
        sliderValue.setText(String.valueOf(Shape.DEFAULT_STROKE));
        this.add(sliderLabel);
        this.add(slider);
        this.add(sliderValue);
    }

    /**
     * Sets the topbar delegate.
     *
     * @param iTopBar the delegate.
     */
    public void setiTopBar(ITopBar iTopBar) {
        this.iTopBar = iTopBar;
    }

    public JButton createButton(String name) {
        JButton button = new JButton(name);
        button.setToolTipText(name);
        button.setForeground(Color.LIGHT_GRAY);
        this.add(button);
        return button;
    }


    @Override
    public void changeFillColor(Color color) {
        this.fillColor.setBackground(color);
    }

    @Override
    public void changeStrokeColor(Color color) {
        this.strokeColor.setBackground(color);
    }
}
