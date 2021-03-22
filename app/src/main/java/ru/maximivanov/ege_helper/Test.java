package ru.maximivanov.ege_helper;

import android.content.Intent;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

public class Test {
    byte id;
    private ArrayList <Task> tasks;
    private final int taskAmount; // количество тестовых заданий в предмете
    private int testScore = 0; // сколько баллов набрал пользователь

    public Test(byte subId) {
        this.id = subId;
        taskAmount = SubjectsList.getSubject(subId).taskAmount;
        tasks = new ArrayList<>(taskAmount);
    }

    public Test(byte subId, int score) {
        id = subId;
        taskAmount = SubjectsList.getSubject(subId).taskAmount;
        testScore = score;
    }

    public int getTestScore() {
        return testScore;
    }

    public void incrementTestScore() {
        testScore++;
    }

    public int getTaskAmount() {
        return taskAmount;
    }

    public void set(FragmentManager fm) {
        for (byte i = 1; i <= taskAmount; ++i) {
            TaskFragment fragment = (TaskFragment) fm
                    .findFragmentByTag(String.valueOf(i));
            assert fragment != null;
            try {
                fragment.set(i, i, User.getSubject(id).tasksNames[i-1],
                        String.valueOf(tasks.get(i-1).getTaskText()));
            }
            catch (NullPointerException e) {
                fragment.set(i, i, User.getSubject(id).name,
                        String.valueOf(tasks.get(i-1).getTaskText()));
            }
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void finish(TestActivity context) {
        // закончить выполнение теста
        int length = User.userStatistic.testResultsSize();
        Files.writeInt(Files.keys[3], length + 1);
        User.userStatistic.addTest(this);
        Files.writeInt(Files.keys[4] + length, id);
        Files.writeInt(Files.keys[5] + length, testScore);
        Intent finishTestIntent = new Intent(context, TestFinishActivity.class);
        context.startActivity(finishTestIntent);
        context.finish();
    }
}
