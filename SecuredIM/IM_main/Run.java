package IM_main;
import IM_GUI.MainFrame;
public class Run {

    private static MainFrame mf;

    public static void main(String [] args) {
        // initialize main window
        mf = getMainFrame();
    }

    // singleton mainframe
    private static MainFrame getMainFrame() {
        if(mf == null)
            return new MainFrame();
        else return mf;
    }

}
