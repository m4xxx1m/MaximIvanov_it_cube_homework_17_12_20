package ru.maximivanov.ege_helper;

import android.content.Context;
import android.content.Intent;

/*
    id предметов:
        0 - Русский язык
        1 - Математика профиль
        2 - Информатика
        3 - Физика
        4 - Химия
        5 - Биология
        6 - Обществознание
        7 - Литература
        8 - География
        9 - История
        10 - Английский язык
*/

public class Subject {
    protected final byte id;
    protected final String name;
    public final byte taskAmount;
    private Theory theory;
    protected Integer[] tasksAnswersScore;
    protected String[] tasksNames;

    public Subject(byte id) {
        this(id, SubjectsList.getSubject(id).name, SubjectsList.getSubject(id).taskAmount);
//        tasksAnswersScore = new Integer[taskAmount];
    }

    public Theory getTheory() {
        return theory;
    }

    public void makeCommonTest(Context context) {
        Intent toTest = new Intent(context, TestActivity.class);
        toTest.putExtra("subject", id);
        toTest.putExtra("isCommon", true);
        context.startActivity(toTest);

    }

    public void setTasksAnswersScore(Integer[] scoreArr) {
        if (tasksAnswersScore.length == scoreArr.length) {
            tasksAnswersScore = scoreArr;
        }
    }

    public void makeOneTaskTest(Context context, byte taskNum) {
        Intent toTest = new Intent(context, TestActivity.class);
        toTest.putExtra("subject", id);
        toTest.putExtra("isCommon", false);
        toTest.putExtra("taskNum", taskNum);
        context.startActivity(toTest);
    }

    public Subject(byte id, String name, byte taskAmount) {
        this.id = id;
        this.name = name;
        this.taskAmount = taskAmount;
        tasksAnswersScore = new Integer[taskAmount];
        theory = new Theory(this.id);
        setTasksNames();
    }

    public void setTheory() { this.theory = new Theory(id); }

    private void setTasksNames() {
        switch (id) {
            case 0:
                tasksNames = new String[]{"Определение главной информации текста", "Средства связи предложений в тексте", "Определение лексического значения слова",
                "Постановка ударения", "Употребление паронимов", "Лексические нормы", "Морфологические нормы (образование форм слова)",
                "Синтаксические нормы. Нормы согласования. Нормы управления", "Правописание корней", "Правописание приставок",
                "Правописание суффиксов (кроме -Н-/-НН-)", "Правописание личных окончаний глаголов и суффиксов причастий",
                "Правописание НЕ и НИ", "Слитное, дефисное, раздельное написание слов", "Правописание -Н- и -НН- в суффиксах",
                "Пунктуация в сложносочиненном предложении и в предложении с однородными членами", "Знаки препинания в предложениях с обособленными членами",
                "Знаки препинания при словах и конструкциях, не связанных с членами предложения", "Знаки препинания в сложноподчиненном предложении",
                "Знаки препинания в сложных предложениях с разными видами связи", "Постановка знаков препинания в различных случаях",
                "Смысловая и композиционная целостность текста", "Функционально-смысловые типы речи", "Лексическое значение слова",
                "Средства связи предложений в тексте", "Языковые средства выразительности"};
                break;
            case 1:
//                tasksNames = new String[]{};
                tasksNames = new String[taskAmount];
                break;
            case 2:
//                tasksNames = new String[]{};
                tasksNames = new String[taskAmount];
                break;
            case 3:
//                tasksNames = new String[]{};
                tasksNames = new String[taskAmount];
                break;
            case 4:
                tasksNames = new String[taskAmount];
//                tasksNames = new String[]{};
                break;
            case 5:
                tasksNames = new String[taskAmount];
//                tasksNames = new String[]{};
                break;
            case 6:
                tasksNames = new String[taskAmount];
//                tasksNames = new String[]{};
                break;
            case 7:
                tasksNames = new String[taskAmount];
//                tasksNames = new String[]{};
                break;
            case 8:
                tasksNames = new String[taskAmount];
//                tasksNames = new String[]{};
                break;
            case 9:
                tasksNames = new String[taskAmount];
//                tasksNames = new String[]{};
                break;
            case 10:
                tasksNames = new String[taskAmount];
//                tasksNames = new String[]{};
                break;
        }
    }

    public void ansScoreInc(int i) {
        if (tasksAnswersScore[i] != null)
            tasksAnswersScore[i] = tasksAnswersScore[i] + 1;
        else
            tasksAnswersScore[i] = 1;
    }

    public void ansScoreDec(int i) {
        if (tasksAnswersScore[i] != null)
            tasksAnswersScore[i] = tasksAnswersScore[i] - 1;
        else
            tasksAnswersScore[i] = -1;
    }
}
