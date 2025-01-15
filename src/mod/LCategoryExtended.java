package mod;

import arc.graphics.Color;
import mindustry.gen.Icon;
import mindustry.logic.LCategory;
import mindustry.ui.Fonts;

public class LCategoryExtended {

    public static final LCategory
            object = new LCategory(
                    "object",
                    new Color(0xE3A015ff),
                    Icon.fileSmall
            ),
            string = new LCategory(
                    "string",
                    new Color(0xce834aff),
                    Icon.bookOpenSmall
            )
    ;

}
