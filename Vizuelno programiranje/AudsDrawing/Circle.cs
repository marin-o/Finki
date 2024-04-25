using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsDrawing {
    public class Circle : Shape {
        public Circle(Color color, int size, Point location) : base(color, size, location) {
        }

        public override void Draw(Graphics g) {
            Brush brush = new SolidBrush(Color);
            g.FillEllipse(brush, Location.X-Size, Location.Y-Size, 2*Size, 2*Size);
            if (Selected) {
                Pen pen = new Pen(Color.Blue, 2);
                g.DrawEllipse(pen, Location.X - Size, Location.Y - Size, 2 * Size, 2 * Size);
                pen.Dispose(); 
            }
            brush.Dispose();
        }

        public override void Pulse() {
            Size += Coef * 3;
            if(Size <= 3 || Size >= 30) {
                Coef *= -1;
            }

        }

        public override bool IsHit(Point p) {
            
            return Math.Sqrt(Math.Pow(p.X - Location.X, 2) + Math.Pow(p.Y - Location.Y, 2)) <= Size;
        }

        
    }
}
