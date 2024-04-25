using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniBlackHoles {
    public class Ball {
        public static Random Random = new Random();
        public Point Center { get; set; }
        public Color Color { get; set; } = Color.Blue;
        public static int RADIUS { get; set; } = 25;
        private int directionX = 0; // 1 -> ++x, -1 -> --x
        private int directionY = 0; // 1 -> ++y, -1 -> --y

        public Ball(Point center, Color color) {
            Center = center;
            Color = color;
            directionX = Random.Next(0, 2) * 2 - 1;
            directionY = Random.Next(0, 2) * 2 - 1;
        }

        public void Draw(Graphics g) {
            Brush b = new SolidBrush(Color);
            g.FillEllipse(b, Center.X - RADIUS, Center.Y - RADIUS, RADIUS * 2, RADIUS * 2);
            b.Dispose();
        }

        public void Move(int maxWidth, int maxHeight) {
            if (directionX > 0 && directionY > 0 ) {
                Center = new Point(Center.X + 10, Center.Y + 10);
                if(Center.X > maxWidth) {
                    directionX *= -1;
                }
                if (Center.Y > maxHeight){
                    directionY *= -1;
                }
            }
            else if (directionX < 0 && directionY > 0) {
                Center = new Point(Center.X - 10, Center.Y + 10);
                if( Center.X < 0 ) {
                    directionX *= -1;
                }
                if( Center.Y > maxHeight ) {
                    directionY *= -1;
                }
            }
            else if ( directionX > 0 && directionY < 0 ) {
                Center = new Point(Center.X + 10, Center.Y - 10);
                if( Center.X > maxWidth ) {
                    directionX *= -1;
                }
                if( Center.Y < 0 ) {
                    directionY *= -1;
                }
            }
            else {
                Center = new Point(Center.X - 10, Center.Y - 10);
                if( Center.X < 0 ) {
                    directionX *= -1;
                }
                if( Center.Y < 0 ) {
                    directionY *= -1;
                }
            }
        }
    }
}
