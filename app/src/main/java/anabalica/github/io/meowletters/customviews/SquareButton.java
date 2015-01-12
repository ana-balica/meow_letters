package anabalica.github.io.meowletters.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableRow.LayoutParams;

import java.util.HashMap;

import anabalica.github.io.meowletters.R;

/**
 * SquareButton width and height are set according to the CustomTableRow width
 * and custom attribute columnsCount, by dividing CustomTableRow width to the
 * columnsCount.
 *
 * @author Ana Balica
 */
public class SquareButton extends Button {
    private Integer row;
    private Integer column;
    private HashMap<String, Integer> position = new HashMap<>(2);
    LayoutParams params = new LayoutParams();

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.getTheme().obtainStyledAttributes(
                attrs, R.styleable.SquareButton, 0, 0);

        try {
            row = attributes.getInteger(R.styleable.SquareButton_row, 0);
            column = attributes.getInteger(R.styleable.SquareButton_column, 0);
        } finally {
            attributes.recycle();
        }
        position.put("row", row);
        position.put("column", column);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        CustomTableRow parent = (CustomTableRow) getParent();
        final int parentWidth = parent.getMeasuredWidth();
        final int columnsCount = parent.getColumnsCount();
        // TODO: adjust the margin according to the screen resolution
        int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics());
        final int width = (parentWidth / columnsCount) - margin * 2;

        setMeasuredDimension(width, width);
        params.setMargins(margin, margin, margin, margin);
        setLayoutParams(params);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public HashMap<String, Integer> getPosition() {
        return position;
    }
}
