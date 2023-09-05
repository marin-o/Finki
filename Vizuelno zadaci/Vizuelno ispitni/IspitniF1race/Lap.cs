using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniF1race {
    public class Lap {
        public int Minutes { get; set; } = 0;
        public int Seconds { get; set; } = 0;

        public Lap(int minutes, int seconds) {
            Minutes = minutes;
            Seconds = seconds;
        }

        public override string ToString() {
            return $"{Minutes}:{Seconds:D2}";
        }
        public int getSeconds() { return Seconds+Minutes*60; }
    }
}
