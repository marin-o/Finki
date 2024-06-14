using System.ComponentModel.DataAnnotations;
using System;

namespace ConcertAdminApplication.Models {
    public class Ticket : BaseEntity {
        public Guid ConcertId { get; set; }
        public Concert? Concert { get; set; }

        [Required]
        public double Price { get; set; }

        [Required]
        public double Rating { get; set; }
        public virtual EShopApplicationUser? CreatedBy { get; set; }
        public virtual ICollection<TicketInShoppingCart>? ProductsInShoppingCart { get; set; }
        public ICollection<TicketInOrder>? ProductInOrders { get; set; }
    }
}
