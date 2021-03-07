package com.sashymov.restaurantservice.models.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "file_id")
    private Long id;
    @Column(name = "file_name")
    private String fileName;
    @Column(name = "download_uri")
    private String fileDownloadUri;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "file_size")
    private long size;
}
