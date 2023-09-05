using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IspitniPolygons {
    [Serializable] public class Polygon {
        public List<Point> Points { get; set; }
        public bool IsClosed { get; set; } = false;
        public bool CloseToStart { get; set; } = false;
        public Point CursorLocation { get; set; }
        public Color Color { get; set; } = Color.Blue;

        public Polygon(Color color) { Color = color;  Points = new List<Point>(); }
        public void AddPoint(Point point) {
            if( CloseToStart ) {
                Points.Add(Points[0]);
                IsClosed = true;
            }
            else Points.Add(point);
        }

        public void MoveCursor(Point cursor) {
            if( Points.Count > 2 ) {
                Point start = Points[0];
                var distance = Math.Sqrt(Math.Pow(start.X - cursor.X, 2) + Math.Pow(start.Y - cursor.Y, 2));
                CloseToStart = (distance < 5);
            }
            CursorLocation = cursor;
        }

        public void Draw(Graphics g) {
            Pen p = new Pen(Color.Black);
            if(Points.Count > 1 ) {
                g.DrawLines(p,Points.ToArray<Point>());

                if(CloseToStart ) {
                    p.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;
                    g.DrawRectangle(p, Points[0].X - 5, Points[0].Y - 5, 10, 10);
                    p.DashStyle = System.Drawing.Drawing2D.DashStyle.Solid;
                }
                if( IsClosed ) {
                    Brush b = new SolidBrush(Color);
                    g.FillPolygon(b, Points.ToArray<Point>());
                    CloseToStart = false;
                    b.Dispose();
                }
            }
            if(Points.Count > 0 && !IsClosed) {
                p.DashStyle = System.Drawing.Drawing2D.DashStyle.DashDotDot;
                g.DrawLine(p, Points[Points.Count - 1].X, Points[Points.Count - 1].Y, CursorLocation.X, CursorLocation.Y);
            }
            p.Dispose();
        }

        internal void Move(Keys keycode) {
            switch (keycode) {
                case Keys.Left:
                    for (int i = 0; i<Points.Count; ++i ) {
                        Points[i] = new Point(Points[i].X - 5, Points[i].Y);
                    }
                    break;
                case Keys.Right:
                    for( int i = 0; i < Points.Count; ++i ) {
                        Points[i] = new Point(Points[i].X + 5, Points[i].Y);
                    }
                    break;
                case Keys.Up:
                    for( int i = 0; i < Points.Count; ++i ) {
                        Points[i] = new Point(Points[i].X, Points[i].Y - 5);
                    }
                    break;
                default:
                    for( int i = 0; i < Points.Count; ++i ) {
                        Points[i] = new Point(Points[i].X, Points[i].Y + 5);
                    }
                    break;
            }
        }
    }
}
