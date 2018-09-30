package com.skywatchersplayground.blitzq2;

import android.provider.BaseColumns;

public final class QuizContract
{
    private QuizContract()
    {
    }

    public static class QuestionsTable implements BaseColumns
    {
        public static final String TABLE_NAME = "QuizQuestions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_OPTION4 = "option4";
        public static final String COLUMN_CORRECT = "correct";
        public static final String COLUMN_TRIVIA = "trivia";
    }
}
