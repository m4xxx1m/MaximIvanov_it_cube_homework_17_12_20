package ru.maximivanov.ege_helper;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Comparator;

// класс для работы с файлами
// работа с файлами реализована в виде словаря, с использованием SharedReferences
public class Files {
    private static String refKey;
    private static Context context;

    public static void initialize(Context context) {
        Files.context = context;
        refKey = context.getString(R.string.preference_file_key);
    }

    public static void writeInt(String key, int fileContents) {
        SharedPreferences sharedPref = context.getSharedPreferences(refKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, fileContents);
        editor.apply();
    }

    public static void read() {
        SharedPreferences sharedPref = context.getSharedPreferences(refKey, Context.MODE_PRIVATE);

        User.isInitialized = sharedPref.getInt(keys[0], 0) != 0;

        int chosenSubjectSize = sharedPref.getInt(keys[1], 0);

        ArrayList<Byte> chosenSubject = new ArrayList<>();
        while (chosenSubjectSize > 0) {
            chosenSubject.add((byte) sharedPref.getInt(keys[2] + chosenSubjectSize, -1));
            chosenSubjectSize--;
        }
        chosenSubject.sort(Comparator.naturalOrder());
        User.setUserSubjectsId(chosenSubject);

        int testsAmount = sharedPref.getInt(keys[3], 0);
        for (int i = 0; i < testsAmount; ++i) {
            Test test = new Test((byte) sharedPref.getInt(keys[4] + i, -1),
                    sharedPref.getInt(keys[5] + i, 0));
            User.userStatistic.addTest(test);
        }
    }

    public static final String[] keys = {"User.isInitialized", "chosenSubjects.size", "chosenSubject",
        "testsAmount", "testSubId", "testsScore"};
}