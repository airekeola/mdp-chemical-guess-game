package com.airekeola.chemicalguessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.airekeola.chemicalguessgame.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;
    private lateinit var chemicals: ArrayList<String>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(layoutInflater);
//        setContentView(R.layout.activity_main)
        setContentView(binding.root);

        // init chemicals
        chemicals = mutableListOf("Hydrogen", "Carbon", "Oxygen") as ArrayList<String>;
        binding.messageTextView.text = "";

        // bind handlers
        binding.guessBtn.setOnClickListener(this::onGuessBtnClick);
        binding.addBtn.setOnClickListener(this::onAddBtnClick);
    }

    private fun onGuessBtnClick(view: View) {
        val guessedChemical = binding.guessText.text.toString();
        if(guessedChemical.isEmpty()){
            binding.messageTextView.text = "Guess chemical is required.";
        }else{
            val correctChemical:String = chemicals[Random.nextInt(from = 0, until = chemicals.size-1)];
            binding.messageTextView.text = "Chemical '$guessedChemical' is already available.";

            if(guessedChemical.equals(correctChemical, ignoreCase = true)){
                binding.messageTextView.text = "Congratulations! You guessed it right. It was $correctChemical.";
            }else{
                binding.messageTextView.text = "Sorry, wrong guess. The correct answer was $correctChemical.";
            }
            binding.guessText.setText("");
        }
    }

    private fun onAddBtnClick(view: View) {
        val newChemical = binding.newChemicalText.text.toString();
        if(newChemical.isEmpty()){
            binding.messageTextView.text = "New chemical name is required.";
        }else if(chemicals.any{c -> c.equals(newChemical, ignoreCase = true)}){
            binding.messageTextView.text = "Chemical '$newChemical' is already available.";
        }else{
            chemicals.add(newChemical);
            binding.newChemicalText.setText("");
            binding.messageTextView.text = "Chemical '$newChemical' added successfully.";
            println(chemicals);
        }
    }
}
