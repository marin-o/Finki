using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudKladilnica {
    public class GameForTicket {
        private string[] tip= { "1", "X", "2" };
        public Game Game { get; set; }
        public int Tip { get; set; }

        public GameForTicket(Game game, int tip) {
            Game = game;
            Tip = tip;
        }

        public override string ToString() {
            return $"{Game.ToString()} - {tip[Tip]}";
        }
    }
}
