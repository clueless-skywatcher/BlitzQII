package com.skywatchersplayground.blitzq2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.skywatchersplayground.blitzq2.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuizQuestions.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_CORRECT + " INTEGER, " +
                QuestionsTable.COLUMN_TRIVIA + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Which of the following words comes from the name of a Hindu deity?",
                "Borborygmus",
                "Amphisbaena",
                "Juggernaut",
                "Cacoethes",
                3,
                "The term \"Juggernaut\" was coined when the British witnessed the Rath Yatra, that concerned the Hindu deity Jagannath in a colossal chariot, and hence the name.");
        addQuestion(q1);
        Question q2 = new Question("If you are standing somewhere near the world's largest overhead water reservoir, you are in which city?",
                "Bangalore",
                "Kolkata",
                "Sydney",
                "Addis Ababa",
                2,
                "The Tala water tank of Kolkata Municipal Corporation was built in 1909. It has the capacity to hold 9 million gallons of water and is the largest overhead reservoir in the world. It has a height of 110 feet.");
        addQuestion(q2);
        Question q3 = new Question("According to some conspiracy theories, Adolf Hitler is alleged to have fled to which country in a U-Boat, instead of committing suicide in his bunker?",
                "Argentina",
                "Austria",
                "Spain",
                "Greece",
                1,
                "\"Grey Wolf: The Escape of Adolf Hitler\", by British authors Simon Dunstan and Gerrard Williams, suggests that Hitler did not commit suicide, but actually escaped to Argentina.");
        addQuestion(q3);
        Question q4 = new Question("\"Colony\" is not the name of a collective group of which animals?",
                "Badgers",
                "Ants",
                "Auks",
                "Spiders",
                4,
                "A group of spiders is called a cluster or a clutter.");
        addQuestion(q4);
        Question q5 = new Question("\"Float like a butterfly, sting like a bee\", the one who is credited with this quote was nicknamed what?",
                "Bard of Avon",
                "King of Rock and Roll",
                "Louisville Lip",
                "Slim Shady",
                3,
                "Muhammad Ali, born Cassius Marcellus Clay Jr, also called the Louisville Lip, was an American professional boxer, activist, and philanthropist. He is widely regarded as one of the most significant and celebrated sports figures of the 20th century.");
        addQuestion(q5);
        Question q6 = new Question("How many stars are there in the flag of the Republic of China?",
                "5",
                "4",
                "3",
                "2",
                1,
                "The flag of China was officially adopted on October 1, 1949. The red of the Chinese flag symbolizes the communist revolution, and it's also the traditional color of the people. The large gold star represents communism, while the four smaller stars represent the social classes of the people.");
        Question q7 = new Question("",
                "5",
                "4",
                "3",
                "2",
                1,
                "The flag of China was officially adopted on October 1, 1949. The red of the Chinese flag symbolizes the communist revolution, and it's also the traditional color of the people. The large gold star represents communism, while the four smaller stars represent the social classes of the people.");
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_CORRECT, question.getAnswerNo());
        cv.put(QuestionsTable.COLUMN_TRIVIA, question.getTrivia());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNo(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CORRECT)));
                question.setTrivia(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_TRIVIA)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
