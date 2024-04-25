using System;
using System.Collections.Generic;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsFlyingBalls {
    [Serializable]
    public class Ball {

        [NonSerialized] public static int RADIUS = 40;
        [NonSerialized] public static Random RANDOM = new Random();
        public Point Center { get; set; }
        public int State { get; set; } // 0 = crveno, 1 = sino, 2 = zeleno, 3 = za brisenje, -1 = lost

        public Ball(Point center) {
            Center = center;
            State = RANDOM.Next(3);
        }

        

        public void Draw(Graphics g) {
            Color color;
            switch( State ) {
                case 0:
                    color = Color.Red;
                    break;
                case 1:
                    color = Color.Blue; break;
                default: color = Color.Green; break;
            }
            Brush brush = new SolidBrush( color );
            g.FillEllipse(brush,Center.X-RADIUS, Center.Y - RADIUS, 2 * RADIUS, 2 * RADIUS);
        }

        public void Move(int dx,  int dy) {
            Center = new Point( Center.X + dx, Center.Y + dy );
        }

        public void Hit(Point point) {
            double distance = Math.Sqrt(Math.Pow(Center.X - point.X,2) + Math.Pow(Center.Y - point.Y,2));
            if( distance < RADIUS ) {
                ++State;
            }
        }
    }
}
