using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniElipis {
    [Serializable]
    public class Ellipse {
        [NonSerialized]
        private static Random RANDOM;
        public Point StartPoint { get; set; } = Point.Empty;
        public Point EndPoint { get; set; } = Point.Empty;
        public bool IsClosed{ get; set; } = false;
        public int Red { get; set; } = 5;
        public int Green { get; set; } = 10;
        public int Blue { get; set; } = 15;
        public Color Color { get; set; } = Color.FromArgb(255, 5, 10, 15);

        public Ellipse(Point startPoint, Point endPoint) {

            if(startPoint.X > endPoint.X || startPoint.Y > endPoint.Y ) {
                //swap points
                Point temp = startPoint;
                startPoint = endPoint;
                endPoint = temp;
            } 
            RANDOM = new Random();
            StartPoint = startPoint;
            EndPoint = endPoint;
        }

        public void AddColor() {
            Red += RANDOM.Next(5) % 256;
            Green += RANDOM.Next(10) % 256;
            Blue += RANDOM.Next(15) % 256;
            Red %= 256;
            Green %= 256;
            Blue %= 256;

            Color = Color.FromArgb(255, Red, Green, Blue);
        }

        public void Draw(Graphics g) {
            
            if( !EndPoint.IsEmpty && !StartPoint.IsEmpty) {
                int width = ( EndPoint.X - StartPoint.X );
                int height = ( EndPoint.Y - StartPoint.Y );
                if( IsClosed ) {
                    Brush b = new SolidBrush(this.Color);
                    g.FillEllipse(b, new Rectangle(StartPoint.X, StartPoint.Y, width, height));
                    b.Dispose();
                }
                else {
                    Pen p = new Pen(Color.Black);
                    p.DashStyle = System.Drawing.Drawing2D.DashStyle.Dash;
                    g.DrawEllipse(p, new Rectangle(StartPoint.X, StartPoint.Y, width, height));
                    p.Dispose();
                }
            }
        }

    }
}
