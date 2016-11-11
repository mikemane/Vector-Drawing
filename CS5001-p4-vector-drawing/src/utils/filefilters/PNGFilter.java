package utils.filefilters;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by un4 on 11/11/16.
 */
public class PNGFilter extends FileFilter {
    @Override
    public boolean accept(File file) {
        if (file.isDirectory())
            return false;
        String filename = file.getName();
        return filename.endsWith(".png") || filename.endsWith(".PNG");
    }

    @Override
    public String getDescription() {
        return "*.png,*.PNG";
    }
}
