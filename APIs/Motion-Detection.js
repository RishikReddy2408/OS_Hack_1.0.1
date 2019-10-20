let torusGeometry = new THREE.TorusGeometry(7, 1.6, 4, 3, 6.3);
/*
TorusGeometry(radius, tube , radialSegments , tubularSegments , arc )
radius - Radius of the torus, from the center of the torus to the center of the tube. 1(by default).
tube — Radius of the tube. 0.4(by default).
radialSegments — 8(by default)
tubularSegments — 6(by default)
arc — Central angle. Default is Math.PI * 2.*/
/*defines all necessary parameters in order to do motion detection */
let material = new THREE.MeshBasicMaterial({ color: 0x0071C5 });
let torus = new THREE.Mesh(torusGeometry, material);
scene.add(torus);

// quaternion to update mesh rotation.
const sensorAbs = new AbsoluteOrientationSensor();
sensorAbs.onreading = () => torus.quaternion.fromArray(sensorAbs.quaternion);
sensorAbs.start();

//rotation matrix update mesh rotation .
const sensorRel = new RelativeOrientationSensor();
let rotationMatrix = new Float32Array(16);
sensor_rel.onreading = () => {
    sensorRel.populateMatrix(rotationMatrix);
    torus.matrix.fromArray(rotationMatrix);
}
sensorRel.start();
