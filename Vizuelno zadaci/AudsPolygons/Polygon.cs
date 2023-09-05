using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsPolygons {
    [Serializable]
    public class Polygon {
        public List<Point> Points{ get; set; }
        public bool IsClosed { get; set; } = false;
        public bool CloseToStart { get; set; } = false;
        public Point CursorLocation { get; set; }
        public Polygon() {
            Points = new List<Point>();
        }

        public void AddPoint(Point p) {
            if(CloseToStart) {
                Points.Add(Points[0]);
                IsClosed = true;
            }
            else{
                Points.Add(p);
            }
        }

        public void MoveCursor(Point cursor) {
            if( Points.Count > 2 ) {
                Point start = Points[0];
                var distance = Math.Sqrt(Math.Pow(start.X - cursor.X, 2) + Math.Pow(start.Y - cursor.Y, 2));
                CloseToStart = ( distance < 5 );
            }
            CursorLocation = cursor;
        }

        public void Draw(Graphics g) {
            Pen pen = new Pen(Color.Black);
            if( Points.Count > 1 ) {
                g.DrawLines(pen, Points.ToArray<Point>());

                if( CloseToStart ) {
                    g.DrawEllipse(pen, Points[0].X - 5, Points[0].Y - 5, 10, 10);
                }
                if( IsClosed ) {
                    Brush brush = new SolidBrush(Color.Red);
                    g.FillPolygon(brush, Points.ToArray<Point>());
                    CloseToStart = false;
                    brush.Dispose();
                }
            }
            if(Points.Count > 0 ) {
                pen.DashStyle = System.Drawing.Drawing2D.DashStyle.Dot;
                g.DrawLine(pen, Points[Points.Count - 1].X, Points[Points.Count - 1].Y, CursorLocation.X, CursorLocation.Y);
            }
            pen.Dispose();
        }
    }
}
