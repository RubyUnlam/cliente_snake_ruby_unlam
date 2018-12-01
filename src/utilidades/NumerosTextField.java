package utilidades;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * JText con validacion para solo aceptar numeros.
 */
public class NumerosTextField extends JTextField {

    private static final long serialVersionUID = 1L;

    List<Integer> teclasPermitidas = new ArrayList<Integer>() {{
        add(KeyEvent.VK_BACK_SPACE);
        add(KeyEvent.VK_LEFT);
        add(KeyEvent.VK_RIGHT);
    }};

    @Override
    public void processKeyEvent(KeyEvent ev) {
        if (Character.isDigit(ev.getKeyChar()) ||
                teclasPermitidas.contains(ev.getKeyCode())) {
            super.processKeyEvent(ev);
        }
        ev.consume();
    }

}