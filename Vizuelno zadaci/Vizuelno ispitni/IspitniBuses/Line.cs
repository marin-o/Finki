using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniBuses {
    public class Line {
        public string Destination { get; set; }
        public int Hour { get; set; }
        public int Minute { get; set; }
        public decimal Price { get; set; }

        public Line(string destination, int hour, int minute, decimal price) {
            Destination = destination;
            Hour = hour;
            Minute = minute;
            Price = price;
        }

        public override string ToString() {
            return $"{Hour:D2}:{Minute:D2} - {Destination} - {Price} Ден.";
        }
        
    }
}
