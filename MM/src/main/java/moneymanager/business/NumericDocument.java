package moneymanager.business;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NumericDocument extends PlainDocument {
    private int maxLength;

    public NumericDocument(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        String currentText = getText(0, getLength());

        if ((currentText.length() + str.length()) <= maxLength) {
            if (isValidCharacter(currentText, str)) {
                super.insertString(offset, str, attr);
            }
        }
    }

    private boolean isValidCharacter(String currentText, String newText) {
        // Solo permite dígitos, un punto decimal y el signo negativo
        for (int i = 0; i < newText.length(); i++) {
            char ch = newText.charAt(i);
            if (!Character.isDigit(ch) && ch != '.' && ch != '-') {
                return false;
            }
        }

        // Solo puede haber un signo negativo al principio
        if (newText.contains("-")) {
            if (newText.indexOf('-') > 0 || currentText.contains("-")) {
                return false;
            }
        }

        // Solo puede haber un punto decimal
        if (newText.contains(".") && currentText.contains(".")) {
            return false;
        }

        return true;
    }
}
