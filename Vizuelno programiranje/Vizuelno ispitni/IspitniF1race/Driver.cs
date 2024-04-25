using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniF1race {
    public class Driver {
        public string Name { get; set; }
        public int Age { get; set; }
        public bool IsFirst { get; set; } = false;
        public List<Lap> Laps { get; set; } = new List<Lap>();

        public Driver(string name, int age) {
            Name = name;
            Age = age;
        }

        public void addLap(Lap lap) {
            Laps.Add(lap);
        }

        public override string ToString() {
            return $"{Name} ({Age}) - {(IsFirst?"F":"S")}";
        }
    }
}
