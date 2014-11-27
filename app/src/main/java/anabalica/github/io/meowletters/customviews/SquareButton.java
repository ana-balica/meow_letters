package anabalica.github.io.meowletters.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * SquareButton width and height are set according to the CustomTableRow width
 * and custom attribute columnsCount, by dividing CustomTableRow width to the
 * columnsCount.
 *
 * @author Ana Balica
 */
public class SquareButton extends Button {

    public SquareButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        CustomTableRow parent = (CustomTableRow) getParent();
        final int parentWidth = parent.getMeasuredWidth();
        final int columnsCount = parent.getColumnsCount();
        final int width = parentWidth / columnsCount;

        setMeasuredDimension(width, width);
    }
}
