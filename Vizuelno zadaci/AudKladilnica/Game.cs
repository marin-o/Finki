using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudKladilnica {
    public class Game {
        public string Code { get; set; }
        public Team Home { get; set; }
        public Team Guest { get; set; }
        public decimal Coef1 { get; set; }
        public decimal CoefX { get; set; }
        public decimal Coef2 { get; set; }

        public Game(string code, Team home, Team guest, decimal coef1, decimal coefX, decimal coef2) {
            Code = code;
            Home = home;
            Guest = guest;
            Coef1 = coef1;
            CoefX = coefX;
            Coef2 = coef2;
        }

        public override string ToString() {
            return $"{Code} ({Home.Name} - {Guest.Name})";
        }
    }
}
