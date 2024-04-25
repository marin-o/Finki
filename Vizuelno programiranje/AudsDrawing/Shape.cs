using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsDrawing {
    public abstract class Shape {
        public Color Color{ get; set; }
        public int Size { get; set; }
        public bool Selected { get; set; }
        public Point Location { get; set; }

        public short Coef { get; set; } = -1;

        protected Shape(Color color, int size, Point location) {
            Color = color;
            Size = size;
            Selected = false;
            Location = location;
        }

        abstract public void Draw(Graphics g);

        public abstract void Pulse();
        public abstract bool IsHit(Point p);

        public override bool Equals(object obj) {
            Shape other = obj as Shape;
            if (other == null) {
                return false;
            }
            if(this.Color.Equals(other.Color) && this.Size == other.Size && this.Location.Equals(other.Location)) {
                return true;
            }
            return false;
        }

    }
}
