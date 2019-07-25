package model;

import java.util.ArrayList;

public class IndexProducerModel {

    private String deleteIndex;
    private String renameIndex;
    private String deleteTest;
    private String renameTest;
    private int i = 1;
    private int j = 1;
    private int k = 1;
    private int l = 1;
    private ArrayList<String> arr;

    public IndexProducerModel() {

    }

    public String getDeleteIndex() {
        deleteIndex = "deleteindex" + (i++);
        return deleteIndex;
    }

    public String getRenameIndex() {
        renameIndex = "renameindex" + (j++);
        return renameIndex;
    }

    public String getDeleteTest() {
        deleteTest = "deleteTest" + (k++);
        return deleteTest;
    }

    public String getRenameTest() {
        renameTest = "renameTest" + (l++);
        return renameTest;
    }
}
