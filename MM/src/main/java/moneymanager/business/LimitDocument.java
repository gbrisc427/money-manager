package moneymanager.business;

import javax.swing.*;
import javax.swing.text.*;

public class LimitDocument extends PlainDocument {
    private int limit;

    public LimitDocument(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}