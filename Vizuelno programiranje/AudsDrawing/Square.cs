using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsDrawing {
    public class Square : Shape {
        public Square(Color color, int size, Point location) : base(color, size, location) {
        }

        public override void Draw(Graphics g) {
            Brush brush = new SolidBrush(Color);
            g.FillRectangle(brush, Location.X - Size, Location.Y - Size, 2 * Size, 2 * Size);
            if( Selected ) {
                Pen pen = new Pen(Color.Red, 2);
                g.DrawRectangle(pen, Location.X - Size, Location.Y - Size, 2 * Size, 2 * Size);
                pen.Dispose();
            }
            brush.Dispose();
        }

        public override bool IsHit(Point p) {
            Rectangle r = new Rectangle(Location.X - Size, Location.Y-Size, 2*Size, 2*Size);
            if (r.Contains(p)) {
                return true;
            }
            return false;
        }

        public override void Pulse() {
            Size += Coef * 3;
            if( Size <= 3 || Size >= 30 ) {
                Coef *= -1;
            }
        }
    }
}
