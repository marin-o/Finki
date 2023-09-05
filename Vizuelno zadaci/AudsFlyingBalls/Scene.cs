using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AudsFlyingBalls {
    [Serializable]
    public class Scene {
        [NonSerialized] public static Random Random = new Random();
        public List<Ball> Balls { get; set; }

        public int Height { get; set; }
        public int Width { get; set; }
        public int NotMissed{ get; set; } = 0;
        public int Missed { get; set; } = 0;
        public bool Paused { get; set; } = false;

        public Scene(int height, int width) {
            Balls = new List<Ball>();
            Height = height;
            Width = width;
        }

        public void AddBall() {
            Balls.Add(
                new Ball(
                    new System.Drawing.Point(
                        -Ball.RADIUS, Random.Next(2 * Ball.RADIUS, Height - 2 * Ball.RADIUS)
                        )));
        }

        public void Move() {
            if(Paused) return;
            for ( int i= 0; i < Balls.Count; i++) {
                Balls[i].Move(5,0);
                if( Balls[i].Center.X - Ball.RADIUS > Width ) {
                    Balls[i].State = -1;
                    Missed++;
                    Balls.RemoveAt(i);
                }
            }
        }

        public void Draw(Graphics g) {
            foreach (Ball b in Balls) {
                b.Draw(g);
            }
        }

        internal void Hit(Point location) {
            if( Paused ) return;
            for(int i=0; i<Balls.Count; i++ ) {
                Balls[i].Hit(location);
                if( Balls[i].State == 3 ) {
                    Balls.RemoveAt(i);
                    NotMissed++;
                }
            }
        }
    }
}
