using System.ComponentModel.DataAnnotations;

namespace ConcertAdminApplication.Models {
    public class Concert : BaseEntity {
        [Required]
        public string ConcertName { get; set; }
        [Required]
        public string ConcertDescription { get; set; }
        [Required]
        public string ConcertImage { get; set; }
        [Required]
        public double Rating { get; set; }

        public virtual ICollection<Ticket>? Tickets { get; set; }
    }
}
