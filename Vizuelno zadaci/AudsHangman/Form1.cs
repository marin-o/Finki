using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AudsHangman {
    public partial class Form1 : Form {
        private int timeLeft = 120;
        private Random random;
        public HangmanWord Hangman { get; set; }
        private HashSet<string> dictionary = new HashSet<string>();
        public Form1() {
            InitializeComponent();
            dictionary = new HashSet<string>(System.IO.File.ReadAllLines("hangmanwords.txt"));
            random = new Random();
            string word = dictionary.ElementAt(random.Next(dictionary.Count));
            Hangman = new HangmanWord(word);
            lbTimer.Text = "02:00";
            timer1.Start();
            updateTextBoxes();
        }

        private void updateProgressBars() {
            pbTime.Value = timeLeft;
            pbAttempts.Value = Hangman.GuessesLeft;
        }

        private void timer1_Tick(object sender, EventArgs e) {
            timeLeft--;
            lbTimer.Text = String.Format("{00:00}:{01:00}", timeLeft / 60, timeLeft % 60);
            updateProgressBars();

            if (timeLeft <= 0 ) {
                timer1.Stop();
                tbGuessLetter.Enabled = false;
                DialogResult result = MessageBox.Show("Time up!","You lose!", MessageBoxButtons.RetryCancel);
                if (result == DialogResult.Retry) {
                    restartGame();
                }
                else {
                    this.Close();
                }
            }
        }

        private void restartGame() {
            timeLeft = 120;
            Hangman = new HangmanWord(dictionary.ElementAt(random.Next(dictionary.Count)));
            lbTimer.Text = "02:00";
            tbGuessLetter.Enabled = true;
            updateTextBoxes();
            updateProgressBars();
        }

        private void updateTextBoxes() {
            lbWordState.Text = Hangman.GetMaskedWord();
            tbAlphabet.Text = Hangman.GetMaskedGuessedLetters();
        }

        private void btnGuessLetter_Click(object sender, EventArgs e) {
            if( tbGuessLetter.Text != "" ) {
                Hangman.GuessLetter(tbGuessLetter.Text[0]);
                updateTextBoxes();
                updateProgressBars();
                if( Hangman.Win() ) {
                    if(MessageBox.Show("Again?","You Win!", MessageBoxButtons.YesNo) == DialogResult.Yes ) {
                        restartGame();
                    }
                    else this.Close();
                }
                if( Hangman.Lose() ) {
                    if(MessageBox.Show("Again?","You Lose!", MessageBoxButtons.YesNo) == DialogResult.Yes ) {
                        restartGame();
                    }
                    else this.Close();
                }
                tbGuessLetter.Text = "";
            }
        }
    }
}
