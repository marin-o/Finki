using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniBuses {
    public class Bus {
        public string Registration { get; set; }
        public string Name { get; set; }
        public bool IsLocal { get; set; }
        public List<Line> Lines { get; set; }

        public Bus(string registration, string name, bool isLocal) {
            Registration = registration;
            Name = name;
            IsLocal = isLocal;
            Lines = new List<Line>();
        }

        public void AddLine(Line line) {
            Lines.Add(line);
        }

        public override string ToString() {
            return $"{Name} - {Registration} - {(IsLocal?"L":"M")}";
        }
    }
}
