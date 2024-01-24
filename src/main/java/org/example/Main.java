package org.example;

import org.example.tondeuse.application.TondeuseMowingService;
import org.example.tondeuse.domain.models.Direction;
import org.example.tondeuse.domain.models.Pelouse;
import org.example.tondeuse.domain.models.Tondeuse;
import org.example.tondeuse.domain.ports.input.FileProcessingInputPort;
import org.example.tondeuse.domain.ports.output.FileProcessingOutputPort;
import org.example.tondeuse.infra.adapters.FileInputAdapter;
import org.example.tondeuse.infra.adapters.FileOutputAdapter;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws IOException {


        FileProcessingInputPort fileInputAdapter = new FileInputAdapter();
        FileProcessingOutputPort fileOutputAdapter = new FileOutputAdapter();
        TondeuseMowingService tondeuseMowingService = new TondeuseMowingService(fileInputAdapter, fileOutputAdapter);
        tondeuseMowingService.mowTondeuses();
    }
}