package com.modelgenerated;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

import com.modelgenerated.foundation.config.Config;
import com.modelgenerated.foundation.config.ConfigLocator;
import com.modelgenerated.foundation.debug.Displayable;
import com.modelgenerated.foundation.logging.Logger;
import com.modelgenerated.generator.Generator;
import com.modelgenerated.generator.GeneratorConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generates all sources based on model file.
 *
 */
@Mojo( name = "generate", defaultPhase = LifecyclePhase.GENERATE_SOURCES )
public class GenerateMojo extends AbstractMojo
{
    /**
     * Location of the file.
     */
    @Parameter( defaultValue = "${project.build.directory}", property = "outputDir", required = true )
    private File outputDirectory;

    @Parameter( property = "skip" )
    private String skip;

    @Parameter( property = "bootstrapURL" )
    private String bootstrapURL;

    @Parameter( property = "generatorRoot" )
    private String generatorRoot;

    public void execute() throws MojoExecutionException {
    	System.out.println("bootstrapURL: " + bootstrapURL);
    	System.out.println("generatorRoot: " + generatorRoot);
    	System.setProperty(ConfigLocator.ENV_BOOTSTRAPPATH, bootstrapURL);
    	System.setProperty(GeneratorConfig.ENV_GENERATOR_ROOT, generatorRoot);

    	Logger.debug(this, "execute: " + bootstrapURL);
    	
    	if (!"true".equals(skip)) {
        	Generator.generateAll();
    	}
    }
}
