using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IspitniBlackHoles {
    public class Scene {
        public static Random RANDOM = new Random();
        public List<Ball> Balls { get; set; } = new List<Ball>();
        public List<Ball> BlackHoles { get; set; } = new List<Ball>();

        public void Draw(Graphics g) {
            foreach (Ball b in Balls) { 
                b.Draw(g);
            }
            foreach (Ball b in BlackHoles) {
                b.Draw(g);
            }
        }

        public void Generate(int maxWidth, int maxHeight) {
            for(int i=0; i<5; i++) {
                Point center = new Point(RANDOM.Next(maxWidth-30),RANDOM.Next(maxHeight-30));
                foreach(Ball b in BlackHoles) {
                    if ()
                }
                Ball newHole = new Ball();
            }
        }

    }
}
