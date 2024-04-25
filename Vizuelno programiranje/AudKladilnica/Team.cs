using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudKladilnica {
    public class Team {
        public string Name { get; set; }
        public string Country { get; set; }

        public Team(string name, string country) {
            Name = name;
            Country = country;
        }

        public override string ToString() {
            return $"{Name} - {Country}";
        }
    }
}
