using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniAvtomobili {
    public class Make {
        public string Name { get; set; }
        public string Code { get; set; }

        public Make(string name, string code) {
            Name = name;
            Code = code;
        }

        public override string ToString() {
            return $"{Name}({Code})";
        }
    }
}
