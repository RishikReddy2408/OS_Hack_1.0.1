let torusGeometry = new THREE.TorusGeometry(7, 1.6, 4, 3, 6.3);
let material = new THREE.MeshBasicMaterial({ color: 0x0071C5 });
let torus = new THREE.Mesh(torusGeometry, material);
scene.add(torus);

// Update mesh rotation using quaternion.
const sensorAbs = new AbsoluteOrientationSensor();
sensorAbs.onreading = () => torus.quaternion.fromArray(sensorAbs.quaternion);
sensorAbs.start();

// Update mesh rotation using rotation matrix.
const sensorRel = new RelativeOrientationSensor();
let rotationMatrix = new Float32Array(16);
sensor_rel.onreading = () => {
    sensorRel.populateMatrix(rotationMatrix);
    torus.matrix.fromArray(rotationMatrix);
}
sensorRel.start();
