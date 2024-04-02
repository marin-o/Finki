using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BookingApplication.Models {
    public class BookingList {
        [Key]
        public Guid Id { get; set; }
        public string? OwnerId { get; set; }
        [ForeignKey("OwnerId")]
        public BookingApplicationUser? Owner { get; set; }
        public virtual ICollection<BookReservation>? BookReservations { get; set; }
    }
}
