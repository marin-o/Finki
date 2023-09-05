using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsHangman {
    public class HangmanWord {
        public string Word { get; set; }
        public HashSet<char> CharsNotGuessed { get; set; }
        public HashSet<char> CharsGuessed { get; set; }
        public int GuessesLeft { get; set; }

        public static readonly int MAX_RETRIES = 5;
        public HangmanWord(string word) {
            Word = word;
            GuessesLeft = 0;
            CharsNotGuessed = new HashSet<char>(word.ToUpper());
            CharsGuessed = new HashSet<char>();
        }

        public string GetMaskedWord() {
            StringBuilder sb = new StringBuilder();
            foreach (char c in Word ) {
                if (CharsNotGuessed.Contains(char.ToUpper(c))) {
                    sb.Append('_');
                } else { sb.Append(char.ToUpper(c)); }
                sb.Append(' ');
            }
            return sb.ToString();
        }

        public string GetMaskedGuessedLetters() {
            StringBuilder sb = new StringBuilder();
            for (char c = 'A'; c <= 'Z';c++) {
                if (CharsGuessed.Contains(c)) {
                    sb.Append(char.ToUpper(c));
                } else { 
                    sb.Append('_'); }
                sb.Append(' ');
            }
            return sb.ToString();
        }

        public bool GuessLetter(char letter) {
            letter=char.ToUpper(letter);
            if(CharsGuessed.Contains(letter)) {
                return false;
            } 
            else {
                if (CharsNotGuessed.Contains(letter)) {
                    CharsNotGuessed.Remove(letter);
                } else {                     
                    GuessesLeft++;
                }
                    CharsGuessed.Add(letter);
                return true;
            }
        }

        public bool Win() {
            return CharsNotGuessed.Count == 0;
        }

        public bool Lose() {
            return GuessesLeft == MAX_RETRIES;
        }
    }
}

