package IM_GUI.ListView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by KellyZhang on 2017/12/22.
 */
public class FriendListViewController {

    private ObservableList<String> names;

    public FriendListViewController() {

    }

    public ObservableList<String> getFriendLVC() {
        if(names == null)
            names = FXCollections.observableArrayList(
                    "Julia", "Ian", "Sue", "Matthew", "Hannah", "Stephan", "Denise");

        return names;
    }
}
