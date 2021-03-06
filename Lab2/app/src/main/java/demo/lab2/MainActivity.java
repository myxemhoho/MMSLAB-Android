package demo.lab2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText gamer;
    TextView status;
    RadioButton radioButton;
    RadioButton radioButton2;
    RadioButton radioButton3;
    RadioGroup radioGroup;
    Button play;
    TextView name;
    TextView winner;
    TextView myMora;
    TextView computerMora;

    int mora_gamer = 3;
    String[] MoraString = {"剪刀", "石頭", "布"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();

    }

    @Override
    protected void onStart() {
        super.onStart();
        clearStatus();
        status.setText("輸入玩家名字及出拳後點擊開始遊戲");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton:
                        mora_gamer = 0;
                        break;
                    case R.id.radioButton2:
                        mora_gamer = 1;
                        break;
                    case R.id.radioButton3:
                        mora_gamer = 2;
                        break;
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearStatus();
                if (checkName() && checkMora()) {
                    name.setText(gamer.getText());
                    myMora.setText(MoraString[mora_gamer]);
                    int mora_computer = (int) (Math.random() * 3);
                    computerMora.setText(MoraString[mora_computer]);
                    if ((mora_gamer == 0 && mora_computer == 1) || (mora_gamer == 1 && mora_computer == 2) || (mora_gamer == 2 && mora_computer == 0)) {
                        winner.setText("電腦");
                        status.setText("可惜，電腦獲勝了");
                    } else if (mora_computer == mora_gamer) {
                        winner.setText("平局");
                        status.setText("平局!再試一場看看");
                    } else {
                        winner.setText(gamer.getText());
                        status.setText("恭喜你獲勝了");
                    };
                }
            }
        });
    }

    private void clearStatus() {
        status.setText("");
        name.setText("");
        winner.setText("");
        myMora.setText("");
        computerMora.setText("");
    }

    private boolean checkMora() {
        if (mora_gamer == 3) {
            status.setText("請選擇出拳的種類");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkName() {
        if (gamer.getText().length() == 0) {
            status.setText("請輸入玩家的名字");
            return false;
        } else {
            return true;
        }
    }

    private void initView() {
        gamer = (EditText) findViewById(R.id.gamer);
        status = (TextView) findViewById(R.id.status);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        play = (Button) findViewById(R.id.play);
        name = (TextView) findViewById(R.id.name);
        winner = (TextView) findViewById(R.id.winner);
        myMora = (TextView) findViewById(R.id.myMora);
        computerMora = (TextView) findViewById(R.id.computerMora);
    }
}
