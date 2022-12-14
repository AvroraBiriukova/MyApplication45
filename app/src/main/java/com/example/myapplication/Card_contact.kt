package com.example.myapplication
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dialogYesOrNo

class Card_contact: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_contact)
        title = "Контактная информация"
        val dbHelper = DBHelper(this)
        val TextSurname = findViewById<TextView>(R.id.textViewSurname)
        val TextName = findViewById<TextView>(R.id.textViewName)
        val TextDate = findViewById<TextView>(R.id.textViewBirthday)
        val TextPhone = findViewById<TextView>(R.id.textViewPhone)
        val button = findViewById<Button>(R.id.button)
        val buttonDrop = findViewById<Button>(R.id.button2)
        val buttonChange = findViewById<Button>(R.id.button3)
        val id = intent.getLongExtra("Id",0)
        val objects = dbHelper.getById(id)

        TextSurname.text = objects?.title
        TextName.text = objects?.name
        TextDate.text = objects?.date
        TextPhone.text = objects?.telephone

        TextPhone.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:"+objects?.telephone)
            startActivity(dialIntent)

        }

        buttonChange.setOnClickListener {
            val intent = Intent(this@Card_contact, Change_contact::class.java)
            intent.putExtra("Id", id)
            startActivity(intent)
        }
        buttonDrop.setOnClickListener {
            val uid = id
            dialogYesOrNo(
                this,
                "Вопрос",
                "Вы уверены,что хотите удаить это контакт?",
                DialogInterface.OnClickListener { dialog, id ->
                    dbHelper.remove(uid)
                    val intent = Intent(this@Card_contact, MainActivity::class.java)
                    startActivity(intent)
                })
        }
        button.setOnClickListener {

           val intent = Intent(this@Card_contact, MainActivity::class.java)
            startActivity(intent)
        }
    }
}