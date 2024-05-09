using System.ComponentModel.DataAnnotations;

namespace ConcertAdminApplication.Models {
    public class BaseEntity {
        [Key]
        public Guid Id { get; set; }
    }
}
