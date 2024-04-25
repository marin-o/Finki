using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsLines {
    public class Line {
        public Point Start{ get; set; }
        public Point End { get; set; }
        public Color Color { get; set; }
        public int Thickness { get; set; }

        public Line(Point start, Point end, Color color, int thickness) {
            Start = start;
            End = end;
            Color = color;
            Thickness = thickness;
        }

        public void Draw(Graphics g) {
            Pen pen = new Pen(Color, Thickness);
            g.DrawLine(pen, Start, End);
            pen.Dispose();
        }
    }
}
