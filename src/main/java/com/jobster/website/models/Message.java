package com.jobster.website.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "message"
)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "from_whom")
    @NotEmpty
    @Size(max = 50)
    private String fromWhom;

    @Column(name = "message_body")
    @NotEmpty
    @Size(max = 500)
    private String messageBody;

    @Column(name = "creation_datetime")
    @NotNull
    private Timestamp creationDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
