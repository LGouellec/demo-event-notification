package org.example.demo.controller;

import org.example.demo.service.HostStoreInfo;
import org.example.demo.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InstanceController {

    @Autowired
    private MetadataService metadataService;

    /**
     * Get the metadata for all of the instances of this Kafka Streams application
     *
     * @return List of {@link HostStoreInfo}
     */
    @RequestMapping(value = "/instances", method = RequestMethod.GET, produces = "application/json")
    public List<HostStoreInfo> streamsMetadata() {
        return metadataService.streamsMetadata();
    }

    /**
     * Get the metadata for all instances of this Kafka Streams application that currently
     * has the provided store.
     *
     * @param store The store to locate
     * @return List of {@link HostStoreInfo}
     */
    @RequestMapping(value = "/instances/{storeName}", method = RequestMethod.GET, produces = "application/json")
    public List<HostStoreInfo> streamsMetadataForStore(@PathVariable("storeName") final String store) {
        return metadataService.streamsMetadataForStore(store);
    }

}
