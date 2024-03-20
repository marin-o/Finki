using Lab1.Web.Models.Identity;
using System.ComponentModel.DataAnnotations;

namespace Lab1.Web.Models.Domain {
    public class Ticket {
        [Key]
        public Guid Id { get; set; }
        public Guid TheaterShowId { get; set; }
        public TheaterShow? TheaterShow { get; set; }
        public Guid UserId { get; set; }
        public Lab1User? Lab1User { get; set; }
        public int Price { get; set; }
    }
}
